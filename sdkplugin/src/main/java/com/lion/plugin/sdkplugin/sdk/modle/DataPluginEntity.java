package com.lion.plugin.sdkplugin.sdk.modle;

public class DataPluginEntity {

    /**
     * open_datasdk : ks
     * data : {"jrtt":{"jrttAppId":"184322","jrttChannel":"com.fishing.getfun.gm"},"uc":{"ucAppId":"100766","ucAppName":"quwanbuyu03","ucChannel":"com.fishing.getfun.gm"},"tc":{"tcActionSetId":"tcActionSetId","tcAppSecretKey":"tcAppSecretKey"},"ks":{"ksAppId":"42720","ksAppName":"quwanbuyu03"},"pdd":{"pddAppId":"pddAppId","pddAppSecret":"pddAppSecret"}}
     */

    private StringBuilder open_datasdk;
    private DataBean data;

    public StringBuilder getOpen_datasdk() {
        return open_datasdk;
    }

    public void setOpen_datasdk(StringBuilder open_datasdk) {
        this.open_datasdk = open_datasdk;
    }

    public DataBean getData() {
        if (data == null){
            data = new DataBean();
        }
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * jrtt : {"jrttAppId":"184322","jrttChannel":"com.fishing.getfun.gm"}
         * uc : {"ucAppId":"100766","ucAppName":"quwanbuyu03","ucChannel":"com.fishing.getfun.gm"}
         * tc : {"tcActionSetId":"tcActionSetId","tcAppSecretKey":"tcAppSecretKey"}
         * ks : {"ksAppId":"42720","ksAppName":"quwanbuyu03"}
         * pdd : {"pddAppId":"pddAppId","pddAppSecret":"pddAppSecret"}
         */

        private JrttBean jrtt;
        private UcBean uc;
        private TcBean tc;
        private KsBean ks;
        private PddBean pdd;

        public JrttBean getJrtt() {
            if (jrtt == null){
                jrtt = new JrttBean();
            }
            return jrtt;
        }

        public void setJrtt(JrttBean jrtt) {
            this.jrtt = jrtt;
        }

        public UcBean getUc() {
            if (uc == null){
                uc = new UcBean();
            }
            return uc;
        }

        public void setUc(UcBean uc) {
            this.uc = uc;
        }

        public TcBean getTc() {
            if (tc == null){
                tc = new TcBean();
            }
            return tc;
        }

        public void setTc(TcBean tc) {
            this.tc = tc;
        }

        public KsBean getKs() {
            if (ks == null){
                ks = new KsBean();
            }
            return ks;
        }

        public void setKs(KsBean ks) {
            this.ks = ks;
        }

        public PddBean getPdd() {
            if (pdd == null){
                pdd = new PddBean();
            }
            return pdd;
        }

        public void setPdd(PddBean pdd) {
            this.pdd = pdd;
        }

        public static class JrttBean {
            /**
             * jrttAppId : 184322
             * jrttChannel : com.fishing.getfun.gm
             */

            private String jrttAppId = "";
            private String jrttChannel = "";

            public String getJrttAppId() {
                return jrttAppId;
            }

            public void setJrttAppId(String jrttAppId) {
                this.jrttAppId = jrttAppId;
            }

            public String getJrttChannel() {
                return jrttChannel;
            }

            public void setJrttChannel(String jrttChannel) {
                this.jrttChannel = jrttChannel;
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("{");
                sb.append("\"jrttAppId\":\"")
                        .append(jrttAppId).append('\"');
                sb.append(",\"jrttChannel\":\"")
                        .append(jrttChannel).append('\"');
                sb.append('}');
                return sb.toString();
            }
        }

        public static class UcBean {
            /**
             * ucAppId : 100766
             * ucAppName : quwanbuyu03
             * ucChannel : com.fishing.getfun.gm
             */

            private String ucAppId = "";
            private String ucAppName = "";
            private String ucChannel = "";

            public String getUcAppId() {
                return ucAppId;
            }

            public void setUcAppId(String ucAppId) {
                this.ucAppId = ucAppId;
            }

            public String getUcAppName() {
                return ucAppName;
            }

            public void setUcAppName(String ucAppName) {
                this.ucAppName = ucAppName;
            }

            public String getUcChannel() {
                return ucChannel;
            }

            public void setUcChannel(String ucChannel) {
                this.ucChannel = ucChannel;
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("{");
                sb.append("\"ucAppId\":\"")
                        .append(ucAppId).append('\"');
                sb.append(",\"ucAppName\":\"")
                        .append(ucAppName).append('\"');
                sb.append(",\"ucChannel\":\"")
                        .append(ucChannel).append('\"');
                sb.append('}');
                return sb.toString();
            }
        }

        public static class TcBean {
            /**
             * tcActionSetId : tcActionSetId
             * tcAppSecretKey : tcAppSecretKey
             */

            private String tcActionSetId = "";
            private String tcAppSecretKey = "";

            public String getTcActionSetId() {
                return tcActionSetId;
            }

            public void setTcActionSetId(String tcActionSetId) {
                this.tcActionSetId = tcActionSetId;
            }

            public String getTcAppSecretKey() {
                return tcAppSecretKey;
            }

            public void setTcAppSecretKey(String tcAppSecretKey) {
                this.tcAppSecretKey = tcAppSecretKey;
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("{");
                sb.append("\"tcActionSetId\":\"")
                        .append(tcActionSetId).append('\"');
                sb.append(",\"tcAppSecretKey\":\"")
                        .append(tcAppSecretKey).append('\"');
                sb.append('}');
                return sb.toString();
            }
        }

        public static class KsBean {
            /**
             * ksAppId : 42720
             * ksAppName : quwanbuyu03
             */

            private String ksAppId = "";
            private String ksAppName = "";

            public String getKsAppId() {
                return ksAppId;
            }

            public void setKsAppId(String ksAppId) {
                this.ksAppId = ksAppId;
            }

            public String getKsAppName() {
                return ksAppName;
            }

            public void setKsAppName(String ksAppName) {
                this.ksAppName = ksAppName;
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("{");
                sb.append("\"ksAppId\":\"")
                        .append(ksAppId).append('\"');
                sb.append(",\"ksAppName\":\"")
                        .append(ksAppName).append('\"');
                sb.append('}');
                return sb.toString();
            }
        }

        public static class PddBean {
            /**
             * pddAppId : pddAppId
             * pddAppSecret : pddAppSecret
             */

            private String pddAppId = "";
            private String pddAppSecret = "";

            public String getPddAppId() {
                return pddAppId;
            }

            public void setPddAppId(String pddAppId) {
                this.pddAppId = pddAppId;
            }

            public String getPddAppSecret() {
                return pddAppSecret;
            }

            public void setPddAppSecret(String pddAppSecret) {
                this.pddAppSecret = pddAppSecret;
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("{");
                sb.append("\"pddAppId\":\"")
                        .append(pddAppId).append('\"');
                sb.append(",\"pddAppSecret\":\"")
                        .append(pddAppSecret).append('\"');
                sb.append('}');
                return sb.toString();
            }
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"jrtt\":")
                    .append(jrtt);
            sb.append(",\"uc\":")
                    .append(uc);
            sb.append(",\"tc\":")
                    .append(tc);
            sb.append(",\"ks\":")
                    .append(ks);
            sb.append(",\"pdd\":")
                    .append(pdd);
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"open_datasdk\":\"")
                .append(open_datasdk.toString()).append('\"');
        sb.append(",\"data\":")
                .append(data);
        sb.append('}');
        return sb.toString();
    }
}
