package com.latte.cj.kipris.model.bibliographydetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private BiblioSummaryInfoArray biblioSummaryInfoArray;
    private IpcInfoArray ipcInfoArray;
    private FamilyInfoArray familyInfoArray;
    private AbstractInfoArray abstractInfoArray;
    private InternationalInfoArray internationalInfoArray;
    private ClaimInfoArray claimInfoArray;
    private ApplicantInfoArray applicantInfoArray;
    private InventorInfoArray inventorInfoArray;
    private LegalStatusInfoArray legalStatusInfoArray;
}
