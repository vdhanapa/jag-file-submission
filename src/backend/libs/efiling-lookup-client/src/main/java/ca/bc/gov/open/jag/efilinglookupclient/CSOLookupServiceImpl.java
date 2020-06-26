package ca.bc.gov.open.jag.efilinglookupclient;


import ca.bc.gov.ag.csows.lookups.*;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CSOLookupServiceImpl implements EfilingLookupService {

    private LookupFacadeBean lookupFacadeItf;
    private static final Logger LOGGER = LoggerFactory.getLogger(CSOLookupServiceImpl.class);

    public CSOLookupServiceImpl(LookupFacadeBean lookupFacadeItf) {
        this.lookupFacadeItf = lookupFacadeItf;
    }

    @Override
    public ServiceFee getServiceFee(String serviceId)  {

        // NOTE- "DCFL" is the only string that will work here until we get our service types setup
        if (StringUtils.isEmpty(serviceId)) return null;

        ServiceFee response = null;
        try {
            response = lookupFacadeItf.getServiceFee(serviceId,Date2XMLGregorian(new Date()));
        }
        catch(DatatypeConfigurationException | NestedEjbException_Exception e) {

            LOGGER.error("Error calling getServiceFee: ", e);
        }

        return response;
    }

    /**
     * Helper function to convert a Date to an XMLGregorianCalendar date for sending to SOAP
     * @param date
     * @return XMLGregorianCalendar
     * @throws DatatypeConfigurationException
     */
    private XMLGregorianCalendar Date2XMLGregorian(Date date) throws DatatypeConfigurationException {

        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        return date2;
    }

}
