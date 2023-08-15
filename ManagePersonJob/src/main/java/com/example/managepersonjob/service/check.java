package com.example.managepersonjob.service;

public class check {
    public static void main(String[] args) {
        int e=4;
        try {
            int q=e-1;
            if(e==4){
                throw new Exception("ko dc xoa cai nay");
            }
        }
        catch (Exception er){
            System.out.println(er.getMessage());
        }
    }
}
