package com.example.administrator.a18master.mvplogin.Bean;

public class User {
    private A user;

    public A getUser() {
        return user;
    }

    public void setUser(A user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "User{" +
                "user=" + user +
                '}';
    }

    public static class A {
        private String resume;
        private String address;
        private int gender;
        private String signature;
        private String nickName;
        private String mobile;
        private int language;
        private String avatar;
        private int type;
        private String realName;
        private Object domain;
        private int id;
        private String job;
        private int status;

        public String getResume() {
            return resume;
        }

        public void setResume(String resume) {
            this.resume = resume;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getLanguage() {
            return language;
        }

        public void setLanguage(int language) {
            this.language = language;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public Object getDomain() {
            return domain;
        }

        public void setDomain(Object domain) {
            this.domain = domain;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "User{" +
                    "resume='" + resume + '\'' +
                    ", address='" + address + '\'' +
                    ", gender=" + gender +
                    ", signature='" + signature + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", mobile='" + mobile + '\'' +
                    ", language=" + language +
                    ", avatar='" + avatar + '\'' +
                    ", type=" + type +
                    ", realName='" + realName + '\'' +
                    ", domain=" + domain +
                    ", id=" + id +
                    ", job='" + job + '\'' +
                    ", status=" + status +
                    '}';
        }
    }
}
