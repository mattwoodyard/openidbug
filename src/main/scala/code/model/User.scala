package code {
package model {

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import _root_.net.liftweb.common._

import code.lib._
//import code.lib._


import _root_.org.openid4java.discovery.DiscoveryInformation
import _root_.org.openid4java.message.AuthRequest

import net.liftweb.openid._

/**
 * The singleton that has methods for accessing the database
 */
object User extends User with MetaOpenIDProtoUser[User]{

  def openIDVendor = BasicOpenIdConsumer

  override def homePage = if (loggedIn_?) "/static/index" else "/" 
  override def dbTableName = "users" // define the DB table name
  override def screenWrap = Full(<lift:surround with="login" at="content">
			       <lift:bind /></lift:surround>)
  // define the order fields will appear in forms and output
  override def fieldOrder = List(id, firstName, lastName, email,
  locale, timezone, password, textArea)

  // comment this line out to require email validations
  override def skipEmailValidation = true

  override def loginXhtml = <input type="text" name="openid_identifier" />

}

/**
 * An O-R mapped "User" class that includes first name, last name, password and we add a "Personal Essay" to it
 */
class User extends OpenIDProtoUser[User] { 

  def getSingleton = User // what's the "meta" server

  // define an additional field for a personal essay
  object textArea extends MappedTextarea(this, 2048) {
    override def textareaRows  = 10
    override def textareaCols = 50
    override def displayName = "Personal Essay"
  }
}

}
}
