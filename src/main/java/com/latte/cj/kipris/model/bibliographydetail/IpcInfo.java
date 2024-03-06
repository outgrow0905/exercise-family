package com.latte.cj.kipris.model.bibliographydetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cj_app_ipc_info")
@Entity
public class IpcInfo {
    @Id
    private String applicationNumber; 		//	출원번호
    @Id
    private String ipcDate; // 	IPC개정일자
    @Id
    private String ipcNumber; // 	IPC코드
}
