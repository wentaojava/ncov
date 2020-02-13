package com.wentao.ncov.service.impl;

import com.wentao.ncov.mappers.ProvinceDataMapper;
import com.wentao.ncov.service.ViewDataService;
import com.wentao.ncov.util.response.RestResponse;
import com.wentao.ncov.vo.GetProvinceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wentao
 * @time 2020年02月13日
 * @copyright Gods bless me,code never with bug.
 */
@Service
@Slf4j
public class ViewDataServiceImpl implements ViewDataService {
    @Resource
    private ProvinceDataMapper provinceDataMapper;

    /**
     * 获取省份信息
     *
     * @return java.util.List.com.wentao.ncov.vo.GetProvinceVO
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    @Override
    public RestResponse<List<GetProvinceVO>> getProvince() {
        List<GetProvinceVO> list = provinceDataMapper.selectAllForGetProvince();
        return new RestResponse<>(list);
    }
}
