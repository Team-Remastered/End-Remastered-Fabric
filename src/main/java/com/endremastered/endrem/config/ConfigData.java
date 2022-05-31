package com.endremastered.endrem.config;

import com.google.gson.annotations.SerializedName;

public class ConfigData {

    @SerializedName("End Remastered Eyes")
    public EREye ER_EYES = new EREye(0);

    @SerializedName("Vanilla Eyes Can Be Used")
    public boolean ENDER_EYES_ENABLED = false;

    public static class EREye {
        @SerializedName("Break Probability (decimal form)")
        public float breakProbability;

        private EREye(float breakProbabilityIn) {
            this.breakProbability = breakProbabilityIn;
        }
    }
}
