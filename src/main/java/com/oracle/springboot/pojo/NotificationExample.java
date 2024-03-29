package com.oracle.springboot.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    public NotificationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFabuIsNull() {
            addCriterion("fabu is null");
            return (Criteria) this;
        }

        public Criteria andFabuIsNotNull() {
            addCriterion("fabu is not null");
            return (Criteria) this;
        }

        public Criteria andFabuEqualTo(Long value) {
            addCriterion("fabu =", value, "fabu");
            return (Criteria) this;
        }

        public Criteria andFabuNotEqualTo(Long value) {
            addCriterion("fabu <>", value, "fabu");
            return (Criteria) this;
        }

        public Criteria andFabuGreaterThan(Long value) {
            addCriterion("fabu >", value, "fabu");
            return (Criteria) this;
        }

        public Criteria andFabuGreaterThanOrEqualTo(Long value) {
            addCriterion("fabu >=", value, "fabu");
            return (Criteria) this;
        }

        public Criteria andFabuLessThan(Long value) {
            addCriterion("fabu <", value, "fabu");
            return (Criteria) this;
        }

        public Criteria andFabuLessThanOrEqualTo(Long value) {
            addCriterion("fabu <=", value, "fabu");
            return (Criteria) this;
        }

        public Criteria andFabuIn(List<Long> values) {
            addCriterion("fabu in", values, "fabu");
            return (Criteria) this;
        }

        public Criteria andFabuNotIn(List<Long> values) {
            addCriterion("fabu not in", values, "fabu");
            return (Criteria) this;
        }

        public Criteria andFabuBetween(Long value1, Long value2) {
            addCriterion("fabu between", value1, value2, "fabu");
            return (Criteria) this;
        }

        public Criteria andFabuNotBetween(Long value1, Long value2) {
            addCriterion("fabu not between", value1, value2, "fabu");
            return (Criteria) this;
        }

        public Criteria andJieshouIsNull() {
            addCriterion("jieshou is null");
            return (Criteria) this;
        }

        public Criteria andJieshouIsNotNull() {
            addCriterion("jieshou is not null");
            return (Criteria) this;
        }

        public Criteria andJieshouEqualTo(Long value) {
            addCriterion("jieshou =", value, "jieshou");
            return (Criteria) this;
        }

        public Criteria andJieshouNotEqualTo(Long value) {
            addCriterion("jieshou <>", value, "jieshou");
            return (Criteria) this;
        }

        public Criteria andJieshouGreaterThan(Long value) {
            addCriterion("jieshou >", value, "jieshou");
            return (Criteria) this;
        }

        public Criteria andJieshouGreaterThanOrEqualTo(Long value) {
            addCriterion("jieshou >=", value, "jieshou");
            return (Criteria) this;
        }

        public Criteria andJieshouLessThan(Long value) {
            addCriterion("jieshou <", value, "jieshou");
            return (Criteria) this;
        }

        public Criteria andJieshouLessThanOrEqualTo(Long value) {
            addCriterion("jieshou <=", value, "jieshou");
            return (Criteria) this;
        }

        public Criteria andJieshouIn(List<Long> values) {
            addCriterion("jieshou in", values, "jieshou");
            return (Criteria) this;
        }

        public Criteria andJieshouNotIn(List<Long> values) {
            addCriterion("jieshou not in", values, "jieshou");
            return (Criteria) this;
        }

        public Criteria andJieshouBetween(Long value1, Long value2) {
            addCriterion("jieshou between", value1, value2, "jieshou");
            return (Criteria) this;
        }

        public Criteria andJieshouNotBetween(Long value1, Long value2) {
            addCriterion("jieshou not between", value1, value2, "jieshou");
            return (Criteria) this;
        }

        public Criteria andOuteridIsNull() {
            addCriterion("outerId is null");
            return (Criteria) this;
        }

        public Criteria andOuteridIsNotNull() {
            addCriterion("outerId is not null");
            return (Criteria) this;
        }

        public Criteria andOuteridEqualTo(Long value) {
            addCriterion("outerId =", value, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridNotEqualTo(Long value) {
            addCriterion("outerId <>", value, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridGreaterThan(Long value) {
            addCriterion("outerId >", value, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridGreaterThanOrEqualTo(Long value) {
            addCriterion("outerId >=", value, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridLessThan(Long value) {
            addCriterion("outerId <", value, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridLessThanOrEqualTo(Long value) {
            addCriterion("outerId <=", value, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridIn(List<Long> values) {
            addCriterion("outerId in", values, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridNotIn(List<Long> values) {
            addCriterion("outerId not in", values, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridBetween(Long value1, Long value2) {
            addCriterion("outerId between", value1, value2, "outerid");
            return (Criteria) this;
        }

        public Criteria andOuteridNotBetween(Long value1, Long value2) {
            addCriterion("outerId not between", value1, value2, "outerid");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("createdate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("createdate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterion("createdate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterion("createdate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterion("createdate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterion("createdate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterion("createdate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterion("createdate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterion("createdate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterion("createdate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterion("createdate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterion("createdate not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andNotifiernameIsNull() {
            addCriterion("notifierName is null");
            return (Criteria) this;
        }

        public Criteria andNotifiernameIsNotNull() {
            addCriterion("notifierName is not null");
            return (Criteria) this;
        }

        public Criteria andNotifiernameEqualTo(String value) {
            addCriterion("notifierName =", value, "notifiername");
            return (Criteria) this;
        }

        public Criteria andNotifiernameNotEqualTo(String value) {
            addCriterion("notifierName <>", value, "notifiername");
            return (Criteria) this;
        }

        public Criteria andNotifiernameGreaterThan(String value) {
            addCriterion("notifierName >", value, "notifiername");
            return (Criteria) this;
        }

        public Criteria andNotifiernameGreaterThanOrEqualTo(String value) {
            addCriterion("notifierName >=", value, "notifiername");
            return (Criteria) this;
        }

        public Criteria andNotifiernameLessThan(String value) {
            addCriterion("notifierName <", value, "notifiername");
            return (Criteria) this;
        }

        public Criteria andNotifiernameLessThanOrEqualTo(String value) {
            addCriterion("notifierName <=", value, "notifiername");
            return (Criteria) this;
        }

        public Criteria andNotifiernameLike(String value) {
            addCriterion("notifierName like", value, "notifiername");
            return (Criteria) this;
        }

        public Criteria andNotifiernameNotLike(String value) {
            addCriterion("notifierName not like", value, "notifiername");
            return (Criteria) this;
        }

        public Criteria andNotifiernameIn(List<String> values) {
            addCriterion("notifierName in", values, "notifiername");
            return (Criteria) this;
        }

        public Criteria andNotifiernameNotIn(List<String> values) {
            addCriterion("notifierName not in", values, "notifiername");
            return (Criteria) this;
        }

        public Criteria andNotifiernameBetween(String value1, String value2) {
            addCriterion("notifierName between", value1, value2, "notifiername");
            return (Criteria) this;
        }

        public Criteria andNotifiernameNotBetween(String value1, String value2) {
            addCriterion("notifierName not between", value1, value2, "notifiername");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleIsNull() {
            addCriterion("questionTitle is null");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleIsNotNull() {
            addCriterion("questionTitle is not null");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleEqualTo(String value) {
            addCriterion("questionTitle =", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleNotEqualTo(String value) {
            addCriterion("questionTitle <>", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleGreaterThan(String value) {
            addCriterion("questionTitle >", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleGreaterThanOrEqualTo(String value) {
            addCriterion("questionTitle >=", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleLessThan(String value) {
            addCriterion("questionTitle <", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleLessThanOrEqualTo(String value) {
            addCriterion("questionTitle <=", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleLike(String value) {
            addCriterion("questionTitle like", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleNotLike(String value) {
            addCriterion("questionTitle not like", value, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleIn(List<String> values) {
            addCriterion("questionTitle in", values, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleNotIn(List<String> values) {
            addCriterion("questionTitle not in", values, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleBetween(String value1, String value2) {
            addCriterion("questionTitle between", value1, value2, "questiontitle");
            return (Criteria) this;
        }

        public Criteria andQuestiontitleNotBetween(String value1, String value2) {
            addCriterion("questionTitle not between", value1, value2, "questiontitle");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table notification
     *
     * @mbggenerated do_not_delete_during_merge Wed Sep 25 09:56:03 CST 2019
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table notification
     *
     * @mbggenerated Wed Sep 25 09:56:03 CST 2019
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}