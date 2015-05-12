package server;

public class PoiWebServiceProxy implements server.PoiWebService {
  private String _endpoint = null;
  private server.PoiWebService poiWebService = null;
  
  public PoiWebServiceProxy() {
    _initPoiWebServiceProxy();
  }
  
  public PoiWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initPoiWebServiceProxy();
  }
  
  private void _initPoiWebServiceProxy() {
    try {
      poiWebService = (new server.PoiWebServiceImplServiceLocator()).getPoiWebServiceImplPort();
      if (poiWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)poiWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)poiWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (poiWebService != null)
      ((javax.xml.rpc.Stub)poiWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public server.PoiWebService getPoiWebService() {
    if (poiWebService == null)
      _initPoiWebServiceProxy();
    return poiWebService;
  }
  
  public java.lang.String setMonitorData(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (poiWebService == null)
      _initPoiWebServiceProxy();
    return poiWebService.setMonitorData(arg0, arg1);
  }
  
  public java.lang.String getMapData(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (poiWebService == null)
      _initPoiWebServiceProxy();
    return poiWebService.getMapData(arg0, arg1);
  }
  
  public java.lang.String registerUser(java.lang.String arg0) throws java.rmi.RemoteException{
    if (poiWebService == null)
      _initPoiWebServiceProxy();
    return poiWebService.registerUser(arg0);
  }
  
  public boolean sucsessfulLogIn(java.lang.String arg0) throws java.rmi.RemoteException{
    if (poiWebService == null)
      _initPoiWebServiceProxy();
    return poiWebService.sucsessfulLogIn(arg0);
  }
  
  
}