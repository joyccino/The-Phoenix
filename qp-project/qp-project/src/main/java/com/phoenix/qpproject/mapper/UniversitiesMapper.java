package com.phoenix.qpproject.mapper;

import com.phoenix.qpproject.dto.UniversitiesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UniversitiesMapper {
    public List<UniversitiesDTO> getUniList();
    public UniversitiesDTO getUniByDomain(String unisDomain);
}
