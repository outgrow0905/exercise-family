package com.latte.cj.kipris.model.bibliographydetail;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternationalInfoArray {
    @JacksonXmlElementWrapper(useWrapping = false)
    List<InternationalInfo> internationalInfo = new ArrayList<>();
}
