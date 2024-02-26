package com.latte.cj.kipris.feign;

import com.latte.cj.config.feign.FeignConfig;
import com.latte.cj.config.feign.common.CommonErrorDecoder;
import com.latte.cj.kipris.feign.request.GetRegistrationInfoRequest;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
    value = "kipris-request-client",
    url = "https://plus.kipris.or.kr",
    configuration = {
        FeignConfig.class,
        CommonErrorDecoder.class}
)
public interface KiprisFeignClient {
    @ExternalDocumentation(
        description = "특허번호를 요청값으로 특허관련 정보를 조회",
        url = "https://plus.kipris.or.kr/portal/data/util/DBII_000000000000015/view.do?menuNo=210004&subTab=SC001#soap_ADI_0000000000009942")
    @RequestMapping(method = RequestMethod.GET, value = "/openapi/rest/RegistrationService/registrationInfo")
    String getRegistrationInfo(GetRegistrationInfoRequest request);
}
