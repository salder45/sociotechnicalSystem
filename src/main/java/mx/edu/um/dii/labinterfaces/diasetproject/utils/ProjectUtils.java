/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.utils;

import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;

/**
 *
 * @author laboratoriointerface
 */
public class ProjectUtils {

    public static String generateCode(Long Id, String codeType) {
        String returnValue = codeType;
        Integer lenght = Constants.MAX_SIZE_TEN;
        Integer startValue = 1;
        if (codeType.equals(Constants.WORKORDER_START_CODE)) {
            lenght = Constants.MAX_SIZE_FIFTEEN;
            startValue = 2;
        }

        String stringId = Id.toString();

        for (int i = startValue; i < (lenght - stringId.length()); i++) {
            returnValue += "0";
        }  

        return returnValue+stringId;
    }
}
