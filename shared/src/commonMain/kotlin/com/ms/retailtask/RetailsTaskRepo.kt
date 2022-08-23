package com.ms.retailtask

import model.RetailTaskModel

object RetailsTaskRepo {

    val list = arrayListOf<RetailTaskModel>()
    fun getToDayTaskList():ArrayList<RetailTaskModel>{
        list.clear()
        list.add(RetailTaskModel("1","Task Retails Title",20,Constant.urgent))
        list.add(RetailTaskModel("2","Task Retails Title,Task Retails Title,Task Retails TitleTask Retails Title Task Retails Title,Task Retails Title,Task Retails TitleTask Retails Title Task Retails Title,Task Retails Title,Task Retails TitleTask Retails Title",20,Constant.normal))
        list.add(RetailTaskModel("3","Task Retails Title",20,Constant.urgent))
        list.add(RetailTaskModel("4","Task Retails Title",20,Constant.critical))
        list.add(RetailTaskModel("5","Task Retails Title",20,Constant.urgent))
        list.add(RetailTaskModel("6","Task Retails Title",20,Constant.normal))
        list.add(RetailTaskModel("7","Task Retails Title",20,Constant.urgent))
        list.add(RetailTaskModel("8","Task Retails Title",20,Constant.critical))
        list.add(RetailTaskModel("9","Task Retails Title",20,Constant.urgent))
        list.add(RetailTaskModel("10","Task Retails Title",20,Constant.normal))
        list.add(RetailTaskModel("11","Task Retails Title",20,Constant.urgent))
        list.add(RetailTaskModel("12","Task Retails Title",20,Constant.critical))
        list.add(RetailTaskModel("13","Task Retails Title",20,Constant.urgent))
        list.add(RetailTaskModel("14","Task Retails Title",20,Constant.normal))
        list.add(RetailTaskModel("15","Task Retails Title",20,Constant.urgent))
        list.add(RetailTaskModel("16","Task Retails Title",20,Constant.critical))
        return list
    }

    fun getTaskByID(string: String?):RetailTaskModel {
        if (list.isEmpty()) {
            getToDayTaskList()
        }
        return list.single { it.id == string }
    }
}