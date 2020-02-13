package com.wentao.ncov.controller;

import com.wentao.ncov.service.ViewDataService;
import com.wentao.ncov.util.response.RestResponse;
import com.wentao.ncov.vo.GetProvinceVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 前端获取数据控制层
 *
 * @author wentao
 * @time 2020年02月13日
 * @copyright Gods bless me,code never with bug.
 */
@RestController
@RequestMapping(value = "/viewData")
public class ViewDataController {

    @Resource
    private ViewDataService viewDataService;

    /**
     * 获取省份信息
     *
     * @return java.util.List.com.wentao.ncov.vo.GetProvinceVO
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    @RequestMapping(value = "/getProvince", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<GetProvinceVO>> getProvince() {
        return viewDataService.getProvince();
    }


}
