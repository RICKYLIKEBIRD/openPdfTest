package Dto;

import Util.Base64Util;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PdfDTO {

    private byte[] pdfDateBytes;

    public static PdfDTO valueOf(byte[] bytes) {
        PdfDTO pdfDTO = new PdfDTO();
        pdfDTO.setPdfDateBytes(bytes);
        return pdfDTO;
    }
}
