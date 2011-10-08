package com.digitalbarista.cat.security;

import java.util.List;
import java.util.Map;

import org.springframework.security.acls.model.Acl;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.security.acls.model.ChildrenExistException;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;

public class TestMutableAclService implements MutableAclService {

  public List<ObjectIdentity> findChildren(ObjectIdentity paramObjectIdentity) {
    // TODO Auto-generated method stub
    return null;
  }

  public Acl readAclById(ObjectIdentity paramObjectIdentity)
      throws NotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  public Acl readAclById(ObjectIdentity paramObjectIdentity, List<Sid> paramList)
      throws NotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  public Map<ObjectIdentity, Acl> readAclsById(List<ObjectIdentity> paramList)
      throws NotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  public Map<ObjectIdentity, Acl> readAclsById(List<ObjectIdentity> paramList,
      List<Sid> paramList1) throws NotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

  public MutableAcl createAcl(ObjectIdentity paramObjectIdentity)
      throws AlreadyExistsException {
    // TODO Auto-generated method stub
    return null;
  }

  public void deleteAcl(ObjectIdentity paramObjectIdentity, boolean paramBoolean)
      throws ChildrenExistException {
    // TODO Auto-generated method stub

  }

  public MutableAcl updateAcl(MutableAcl paramMutableAcl)
      throws NotFoundException {
    // TODO Auto-generated method stub
    return null;
  }

}
