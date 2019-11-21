package com.example.group23debug;

public class serviceinclinicinterface {

        private String name;
        private String role;
        private String rate;

        public serviceinclinicinterface(String name , String role ,String rate){
            this.name = name;
            this.role = role;
            this.rate =rate;

        }

        public  serviceinclinicinterface(){}

        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getRole(){
            return this.role;
        }
        public void setRole(String role){
            this.role = role;
        }
        public String getRate(){
            return this.rate;
        }
        public void setRate(String rate){
            this.rate = rate;
        }
}
