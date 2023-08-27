package net.steve.vanillaoverhaulmod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class TestEntity extends Monster  {
    public final AnimationState idleAnimationState = new AnimationState();
    public TestEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public TestEntity(Level level, double x, double y, double z) {
        this(ModEntities.EXAMPLE_ENTITY.get(), level);
        setPos(x, y, z);
    }

    public TestEntity(Level level, BlockPos position) {
        this(level, position.getX(), position.getY(), position.getZ());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(4, new TestEntity.TestEntityAttackGoal(this));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public void aiStep() {
        if (this.getDeltaMovement().y < 0.0D) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
        }

        super.aiStep();
    }

    static class TestEntityAttackGoal extends Goal {
        private final TestEntity testEntity;
        private int attackTime;
        private int lastSeen;

        public TestEntityAttackGoal(TestEntity pTestEntity) {
            this.testEntity = pTestEntity;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity livingentity = this.testEntity.getTarget();
            return livingentity != null && livingentity.isAlive() && this.testEntity.canAttack(livingentity);
        }

        public void start() {
            this.attackTime = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            this.lastSeen = 0;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            ++this.attackTime;
            LivingEntity livingentity = this.testEntity.getTarget();
            if (livingentity != null) {
                boolean flag = this.testEntity.getSensing().hasLineOfSight(livingentity);
                if (flag) {
                    this.lastSeen = 0;
                } else {
                    ++this.lastSeen;
                }

                double d0 = this.testEntity.distanceToSqr(livingentity);
                if (d0 < 4.0D) {
                    if (!flag) {
                        return;
                    }

                    if (this.attackTime <= 0) {
                        this.attackTime = 20;
                        this.testEntity.doHurtTarget(livingentity);
                    }

                    this.testEntity.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
                } else if (d0 < this.getFollowDistance() * this.getFollowDistance() && flag) {
                    double d1 = livingentity.getX() - this.testEntity.getX();
                    double d2 = livingentity.getY(0.5D) - this.testEntity.getY(0.5D);
                    double d3 = livingentity.getZ() - this.testEntity.getZ();

                    if (this.attackTime == 20) {
                        double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
                        if (!this.testEntity.isSilent()) {
                            this.testEntity.level().levelEvent((Player) null, 1018, this.testEntity.blockPosition(), 0);
                        }

                        if (this.testEntity.getRandom().nextFloat() <= 0.5F)
                            doSmallFireballAttack(d1, d2, d3);
                        else
                            doLargeFireballAttack(d1, d2, d3);

                        BlockPos blockPos = new BlockPos((int)this.testEntity.getX(), (int)this.testEntity.getY(), (int)this.testEntity.getZ());
                        this.testEntity.level().setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);

                        this.attackTime = -40;
                    }

                    this.testEntity.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
                } else if (this.lastSeen < 5) {
                    this.testEntity.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
                }

                super.tick();
            }
        }

        private void doSmallFireballAttack(double d1, double d2, double d3) {
            double xOffset = -4.5D;
            int emptyAttack = this.testEntity.getRandom().nextIntBetweenInclusive(1, 6);
            for(int i = 0; i < 8; ++i) {
                if (i != emptyAttack) {
                    SmallFireball smallfireball = new SmallFireball(this.testEntity.level(), this.testEntity, d1, 0.0D, d3);
                    smallfireball.setPos(this.testEntity.getX() + xOffset, this.testEntity.getY(0.5D) + 0.5D, smallfireball.getZ() + 0.5D);
                    this.testEntity.level().addFreshEntity(smallfireball);
                }
                xOffset += 1.5D;
            }
        }

        private void doLargeFireballAttack(double d1, double d2, double d3) {
            LivingEntity livingentity = this.testEntity.getTarget();

            LargeFireball largefireball = new LargeFireball(this.testEntity.level(), this.testEntity, 0.0D, d2, 0.0, 1);
            largefireball.setPos(livingentity.getX(), livingentity.getY() + 15.0D, livingentity.getZ());
            this.testEntity.level().addFreshEntity(largefireball);
        }

        private double getFollowDistance() {
            return this.testEntity.getAttributeValue(Attributes.FOLLOW_RANGE);
        }
    }

   public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.MOVEMENT_SPEED, (double)0.23F)
                .add(Attributes.ATTACK_DAMAGE, 3.0D)
                .add(Attributes.ARMOR, 2.0D);
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 1.74F;
    }

    public static boolean canSpawn(EntityType<TestEntity> entityType, LevelAccessor level, MobSpawnType spawnType, BlockPos position, RandomSource random) {
        return false;
    }
}
