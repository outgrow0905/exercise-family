package com.latte.cj.kipris.feign;

import com.latte.cj.config.feign.FeignConfig;
import com.latte.cj.config.feign.common.CommonErrorDecoder;
import com.latte.cj.kipris.feign.request.GetRegistrationInfoRequest;
import com.latte.cj.kipris.feign.request.GetRoyaltyStatusRequest;
import feign.Param;
import feign.RequestLine;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "kipris-client",
    value = "kipris-client",
    url = "http://plus.kipris.or.kr",
    configuration = {
        FeignConfig.class,
        CommonErrorDecoder.class}
)
public interface KiprisFeignClient {
    @ExternalDocumentation(
        description = "특허번호를 요청값으로 특허관련 정보를 조회",
        url = "http://plus.kipris.or.kr/portal/data/util/DBII_000000000000015/view.do?menuNo=210004&subTab=SC001#soap_ADI_0000000000009942")
    @RequestMapping(method = RequestMethod.GET, value = "/openapi/rest/RegistrationService/registrationInfo")
    String getRegistrationInfo(@SpringQueryMap GetRegistrationInfoRequest request);

    @ExternalDocumentation(
        description = "출원번호(applicationNumber)로 서지정보 조회",
        url = "https://plus.kipris.or.kr/portal/data/service/DBII_000000000000001/view.do?menuNo=210000&kppBCode=&kppMCode=&kppSCode=&subTab=&entYn=N&clasKeyword=#soap_ADI_0000000000002130")
    @RequestMapping(method = RequestMethod.GET, value = "/kipo-api/kipi/patUtiModInfoSearchSevice/getBibliographyDetailInfoSearch")
    String getBiblio(@RequestParam("ServiceKey") String ServiceKey, @RequestParam("applicationNumber") String applicationNumber);
}
