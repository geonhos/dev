package com.demo.embeddedpostgreswithmybatis.mapper;

import com.demo.embeddedpostgreswithmybatis.domain.TestTable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExampleMapper {

    TestTable findById(int id);

}
