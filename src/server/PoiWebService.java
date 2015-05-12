/**
 * PoiWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package server;

public interface PoiWebService extends java.rmi.Remote {
    public java.lang.String registerUser(java.lang.String arg0) throws java.rmi.RemoteException;
    public boolean sucsessfulLogIn(java.lang.String arg0) throws java.rmi.RemoteException;
    public java.lang.String setMonitorData(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public java.lang.String getMapData(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException;
}
