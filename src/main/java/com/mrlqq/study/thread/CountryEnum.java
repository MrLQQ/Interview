package com.mrlqq.study.thread;

import lombok.Getter;

import java.util.concurrent.CountDownLatch;

/**
 * @projectName: Interview
 * @package: com.mrlqq.study.thread
 * @className: CountryEnum
 * @author: LQQ
 * @description: TODO
 * @date: 2022/2/15 19:44
 * @version: 1.0
 *
 * 6国枚举
 */
public enum CountryEnum {
    one(1,"齐"), two(2,"楚"), three(3,"燕"), four(4,"赵"), five(5,"魏"), six(6,"韩");

    @Getter private Integer retCode;
    @Getter private String retMessage;

    CountryEnum(Integer retCode, String retMessage){
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum element : myArray) {
            if (index == element.getRetCode()){
                return element;
            }
        }
        return null;
    }
}
