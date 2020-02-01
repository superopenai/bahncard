package me.superning.luntan.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author superning
 * @time  2020年1月12日
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {



}
