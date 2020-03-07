package com.wentao.ncov.controller;

import com.wentao.ncov.bo.GetCityDataTodayByMongodbIdBO;
import com.wentao.ncov.bo.GetNationalDataByDateBO;
import com.wentao.ncov.exceptionhandler.SystemErrorCode;
import com.wentao.ncov.service.ViewDataService;
import com.wentao.ncov.util.request.RestRequest;
import com.wentao.ncov.util.response.RestResponse;
import com.wentao.ncov.vo.*;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 获取今日疫情中的省份信息
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

    /**
     * 获取全国疫情数据
     *
     * @return com.wentao.ncov.vo.GetProvinceVO
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    @RequestMapping(value = "/getDataToday", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<GetDataTodayVO> getDataToday() {
        return viewDataService.getDataToday();
    }

    /**
     * 获取各省份疫情数据
     *
     * @return com.wentao.ncov.vo.GetProvinceVO
     * @throws
     * @author wentao
     * @time 2020年02月13日
     * Gods bless me,code never with bug.
     */
    @RequestMapping(value = "/getDataTodayForProvince", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<GetDataTodayForMapVO> getDataTodayForProvince() {
        return viewDataService.getDataTodayForProvince();
    }

    /**
     * 根据id获取对应城市数据
     *
     * @param restRequest
     * @return com.wentao.ncov.vo.GetCityDataTodayByMongodbIdVO
     * @throws
     * @author wentao
     * @time 2020年02月14日
     * Gods bless me,code never with bug.
     */
    @RequestMapping(value = "/getCityDataTodayByMongodbId", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<GetCityDataTodayByMongodbIdVO>> getCityDataTodayByMongodbId(@Validated @RequestBody RestRequest<GetCityDataTodayByMongodbIdBO> restRequest) {
        return viewDataService.getCityDataTodayByMongodbId(restRequest.getBody());
    }


    /**
     * 根据开始，结束日期获取全国数据
     *
     * @param restRequest
     * @return GetNationalDataByDateVO
     * @throws
     * @author wentao
     * @time 2020年03月07日
     * Gods bless me,code never with bug.
     */
    @RequestMapping(value = "/getNationalDataByDate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestResponse<List<GetNationalDataByDateVO>> getNationalDataByDate(@Validated @RequestBody RestRequest<GetNationalDataByDateBO> restRequest) {
        GetNationalDataByDateBO bo = restRequest.getBody();
        if (null == bo) {
            return new RestResponse<>().withCode(SystemErrorCode.PARAM_NOT_ALLOW.getCode()).withMessage(SystemErrorCode.PARAM_NOT_ALLOW.getMessage());
        }
        if (bo.getStartDate().after(bo.getEndDate())) {
            return new RestResponse<>().withCode(SystemErrorCode.PARAM_NOT_ALLOW.getCode()).withMessage("开始日期不能大于结束日期");
        }
        return viewDataService.getNationalDataByDate(restRequest.getBody());
    }


}
