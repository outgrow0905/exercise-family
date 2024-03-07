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
public class BiblioSummaryInfoArray {
    // @JacksonXmlElementWrapper(useWrapping = false)
    // private List<BiblioSummaryInfo> biblioSummaryInfo = new ArrayList<>();
    private BiblioSummaryInfo biblioSummaryInfo;
}
