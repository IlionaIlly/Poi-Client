/**
 * PoiWebServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package server;

public class PoiWebServiceImplServiceLocator extends org.apache.axis.client.Service implements server.PoiWebServiceImplService {

    public PoiWebServiceImplServiceLocator() {
    }


    public PoiWebServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PoiWebServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PoiWebServiceImplPort
    private java.lang.String PoiWebServiceImplPort_address = "http://Alinas-MacBook-Pro.local:9999/PoiService/";

    public java.lang.String getPoiWebServiceImplPortAddress() {
        return PoiWebServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PoiWebServiceImplPortWSDDServiceName = "PoiWebServiceImplPort";

    public java.lang.String getPoiWebServiceImplPortWSDDServiceName() {
        return PoiWebServiceImplPortWSDDServiceName;
    }

    public void setPoiWebServiceImplPortWSDDServiceName(java.lang.String name) {
        PoiWebServiceImplPortWSDDServiceName = name;
    }

    public server.PoiWebService getPoiWebServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PoiWebServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPoiWebServiceImplPort(endpoint);
    }

    public server.PoiWebService getPoiWebServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            server.PoiWebServiceImplPortBindingStub _stub = new server.PoiWebServiceImplPortBindingStub(portAddress, this);
            _stub.setPortName(getPoiWebServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPoiWebServiceImplPortEndpointAddress(java.lang.String address) {
        PoiWebServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (server.PoiWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                server.PoiWebServiceImplPortBindingStub _stub = new server.PoiWebServiceImplPortBindingStub(new java.net.URL(PoiWebServiceImplPort_address), this);
                _stub.setPortName(getPoiWebServiceImplPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PoiWebServiceImplPort".equals(inputPortName)) {
            return getPoiWebServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://server/", "PoiWebServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://server/", "PoiWebServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PoiWebServiceImplPort".equals(portName)) {
            setPoiWebServiceImplPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
