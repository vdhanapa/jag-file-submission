package ca.bc.gov.open.jag.efilingcsostarter;

import ca.bc.gov.ag.csows.ceis.CsoAgencyArr;
import ca.bc.gov.ag.csows.ceis.Csows;

import ca.bc.gov.open.jag.efilingcommons.exceptions.EfilingCourtServiceException;
import ca.bc.gov.open.jag.efilingcommons.model.CourtDetails;
import ca.bc.gov.open.jag.efilingcommons.service.EfilingCourtService;
import org.apache.commons.lang3.StringUtils;


public class CsoCourtServiceImpl implements EfilingCourtService {

    private final Csows csows;

    public CsoCourtServiceImpl(Csows csows) {
        this.csows = csows;
    }

    @Override
    public CourtDetails getCourtDescription(String agencyIdentifierCd) {
        if (StringUtils.isBlank(agencyIdentifierCd)) throw new IllegalArgumentException("Agency identifier is required");

        CsoAgencyArr csoAgencyArr = csows.getCourtLocations();
        return csoAgencyArr.getArray().stream()
                .filter(court -> court.getAgenAgencyIdentifierCd().equals(agencyIdentifierCd))
                .findFirst()
                .map(court -> new CourtDetails(court.getAgenId(), court.getAgenAgencyNm()))
                .orElseThrow(() -> new EfilingCourtServiceException("Court not found"));
    }
}