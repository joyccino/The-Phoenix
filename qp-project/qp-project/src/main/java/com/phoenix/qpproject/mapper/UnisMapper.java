package com.phoenix.qpproject.mapper;

import com.phoenix.qpproject.dto.UnisDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UnisMapper {
    public List<UnisDTO> getUniList();
    public UnisDTO getUniByDomain(String unisDomain);
}
