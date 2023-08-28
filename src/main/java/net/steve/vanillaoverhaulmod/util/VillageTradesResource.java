package net.steve.vanillaoverhaulmod.util;

import java.util.ArrayList;

public class VillageTradesResource {
    public class Trade {
        public String type;
        public Values values;

        public Trade(String pType) {
            this.type = pType;
            values = new Values("", 0, "", 0, "", 0);
        }

        public Trade(String pType, String pGive, int pGiveAmount, String pTake, int pTakeAmount) {
            this.type = pType;
            values = new Values(pGive, pGiveAmount, pTake, pTakeAmount, "", 0);
        }

        public Trade(String pType, String pName, int pLevel) {
            this.type = pType;
            values = new Values("", 0, "", 0, pName, pLevel);
        }
    }

    public class Values {
        public String give;
        public int giveAmount;
        public String take;
        public int takeAmount;
        public String name;
        public int level;

        public Values(String pGive, int pGiveAmount, String pTake, int pTakeAmount, String pName, int pLevel) {
            this.give = pGive;
            this.giveAmount = pGiveAmount;
            this.take = pTake;
            this.takeAmount = pTakeAmount;
            this.name = pName;
            this.level = pLevel;
        }
    }

    public class VillagerTrade {
        public String profession;
        public int level;
        public ArrayList<Trade> trades;

        public VillagerTrade(String pProfession, int pLevel) {
            this.profession = pProfession;
            this.level = pLevel;
            this.trades = new ArrayList<Trade>();
        }
    }
}
