package com.lion.plugin.sdkplugin.sdk.modle;

import com.lion.plugin.sdkplugin.sdk.base.BaseResult;

import java.util.List;

public class RealNameInfoEntity extends BaseResult{
    /**
     * realname : false
     * age : 0
     * month_amount : 0
     * pay_rule : [{"min_age":"0","max_age":"17","onetime_limit":"0","month_limit":"0"},{"min_age":"8","max_age":"18","onetime_limit":"50","month_limit":"200"}]
     */

    private boolean realname;
    private String age;
    private String month_amount;
    private List<PayRuleBean> pay_rule;

    public boolean isRealname() {
        return realname;
    }

    public void setRealname(boolean realname) {
        this.realname = realname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMonth_amount() {
        return month_amount;
    }

    public void setMonth_amount(String month_amount) {
        this.month_amount = month_amount;
    }

    public List<PayRuleBean> getPay_rule() {
        return pay_rule;
    }

    public void setPay_rule(List<PayRuleBean> pay_rule) {
        this.pay_rule = pay_rule;
    }

    public static class PayRuleBean {
        /**
         * min_age : 0
         * max_age : 17
         * onetime_limit : 0
         * month_limit : 0
         */

        private String min_age;
        private String max_age;
        private String onetime_limit;
        private String month_limit;

        public String getMin_age() {
            return min_age;
        }

        public void setMin_age(String min_age) {
            this.min_age = min_age;
        }

        public String getMax_age() {
            return max_age;
        }

        public void setMax_age(String max_age) {
            this.max_age = max_age;
        }

        public String getOnetime_limit() {
            return onetime_limit;
        }

        public void setOnetime_limit(String onetime_limit) {
            this.onetime_limit = onetime_limit;
        }

        public String getMonth_limit() {
            return month_limit;
        }

        public void setMonth_limit(String month_limit) {
            this.month_limit = month_limit;
        }

        @Override
        public String toString() {
            return "PayRuleBean{" +
                    "min_age='" + min_age + '\'' +
                    ", max_age='" + max_age + '\'' +
                    ", onetime_limit='" + onetime_limit + '\'' +
                    ", month_limit='" + month_limit + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RealNameInfoEntity{" +
                "realname=" + realname +
                ", age='" + age + '\'' +
                ", month_amount='" + month_amount + '\'' +
                ", pay_rule=" + pay_rule +
                '}';
    }
}
