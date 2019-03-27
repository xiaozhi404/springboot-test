package com.example.springboottest.convert.kotlin

import java.beans.Introspector
import java.beans.PropertyDescriptor
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

/**
 * 描述：通用vo,dto转化器
 * 作用：将po转化成vo, 将dto转化成po
 * 规范：1.po和vo，dto的属性名称一致，类型一致
 * 2.日期类型处理方法：po的日期类型为Date, 与前台互相交流的单位为秒
 * 3.po的属性数量>=vo的属性数量 dto的属性数量<=vo
 * 4.若不符合规范3，则需要在程序进行控制
 * @author caixianzhi
 */
object CommonConverter {

    @Throws(Exception::class)
    fun <T> convertPoToVO(po: Any?, voClass: Class<T>): T? {

        if (null == po) {
            return null
        }

        val vo = voClass.newInstance()
        //1.获取vobean描述器
        val voBeanInfo = Introspector.getBeanInfo(voClass)
        val voPropertyDescriptors = voBeanInfo.propertyDescriptors
        //2.将po的属性设置在vo中
        for (voProp in voPropertyDescriptors) {
            val voPropName = voProp.name
            //排除类本身有的class属性
            if ("class" != voPropName) {
                //遍历vo的属性，并获取po对应的属性描述器
                val poProp = PropertyDescriptor(voPropName, po.javaClass)
                //将po属性的值设置在vo中
                var poPropVal = poProp.readMethod.invoke(po)
                //判断类型是否相同
                val poPropType = poProp.propertyType
                val voPropType = voProp.propertyType
                //若不相同则需要转化
                if (poPropType != voPropType) {
                    //若是date,则需要转成long秒
                    if (poPropType == Date::class.java) {
                        val date = poPropVal as Date
                        poPropVal = date.time / 1000
                    }
                    //LocalDateTime类型,则需要转成long秒
                    if (poPropType == LocalDateTime::class.java) {
                        val localDateTime = poPropVal as LocalDateTime
                        poPropVal = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli()
                    }
                    //。。。。其它类型待扩展
                }
                //将po值设置进vo
                voProp.writeMethod.invoke(vo, poPropVal)
            }
        }
        return vo
    }

    @Throws(Exception::class)
    fun <T> convertPoListToVOList(poList: List<*>?, voClass: Class<T>): List<T> {
        if (null == poList || poList.size == 0) {
            return emptyList()
        }
        val voList = ArrayList<T>()
        for (po in poList) {
            val vo = convertPoToVO(po, voClass)
            if (null != vo) {
                voList.add(vo)
            }
        }
        return voList
    }

    @Throws(Exception::class)
    fun <T> convertDTOToPo(dto: Any?, poClass: Class<T>): T? {

        if (null == dto) {
            return null
        }

        val po = poClass.newInstance()
        //1.获取dto的属性描述器
        val dtoBeanInfo = Introspector.getBeanInfo(dto.javaClass)
        val dtoPropertyDescriptors = dtoBeanInfo.propertyDescriptors

        //2.将dto的属性设置在po中
        for (dtoProp in dtoPropertyDescriptors) {
            val dtoPropName = dtoProp.name
            //排除类本身有的class属性
            if ("class" != dtoPropName) {
                //获取po对应的属性描述器
                val poProp = PropertyDescriptor(dtoPropName, poClass)
                //将dto属性的值设置在po中
                var dtoPropVal = dtoProp.readMethod.invoke(dto)
                //判断类型是否相同
                val poPropType = poProp.propertyType
                val dtoPropType = dtoProp.propertyType
                //若不相同则需要转化
                if (poPropType != dtoPropType) {
                    //若是date,则需要将long秒转成date
                    if (poPropType == Date::class.java) {
                        val date = Date(dtoPropVal as Long * 1000)
                        dtoPropVal = date
                    }
                    //。。。。其它类型待扩展
                }
                //将dto值设置进po
                poProp.writeMethod.invoke(po, dtoPropVal)
            }
        }
        return po
    }

    @Throws(Exception::class)
    fun <T> convertDTOListToPoList(dtoList: List<*>?, poClass: Class<T>): List<T>? {
        if (null == dtoList || dtoList.size == 0) {
            return null
        }
        val poList = ArrayList<T>()
        for (dto in dtoList) {
            val po = convertDTOToPo(dto, poClass)
            if (null != po) {
                poList.add(po)
            }
        }
        return poList
    }
}
