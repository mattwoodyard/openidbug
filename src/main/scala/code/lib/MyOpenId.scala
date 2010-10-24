package code.lib

import _root_.org.openid4java.discovery.DiscoveryInformation
import _root_.org.openid4java.message.AuthRequest

import net.liftweb.openid._
import net.liftweb.common.Full

object BasicOpenIdConsumer extends SimpleOpenIDVendor  { 
  def ext(di: DiscoveryInformation, authReq: AuthRequest): Unit = { 
    import WellKnownAttributes._ 
    WellKnownEndpoints.findEndpoint(di) map {ep => 
      ep.makeAttributeExtension(List( Email,FullName )) foreach {ex =>
        authReq.addExtension(ex)
      }
    } 
  } 
  override def createAConsumer = new OpenIDConsumer[UserType] { 
    beforeAuth = Full(ext _) 
  } 


  override def loginForm = <input type="text" name="openid_identifier" />




}

