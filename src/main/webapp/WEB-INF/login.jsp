<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>

<br>
<br>

 <!-- ...:::: Start Customer Login Section :::... -->
    <div class="customer-login">
        <div class="container">
            <div class="row">
                <!--login area start-->
                <div class="col-lg-6 col-md-6">
                    <div class="account_form" data-aos="fade-up" data-aos-delay="0">
                        <h3>login</h3>
                        <form action="controler" method="POST">
                         <c:if test="${message!=null}">
                         
                        <div class="alert alert-danger" role="alert">${message}</div>
                        </c:if>
                        <c:if test="${idProduit!=null}">
                         
                       <input type='text' name='id' value=idProduit hidden>
                        </c:if>
                            <div class="default-form-box">
                            
                                <label>Username or email <span>*</span></label>
                                <input type="text"  name="login">
                                 <input type="text"  name="connecter" hidden>
                            </div>
                            <div class="default-form-box">
                                <label>Passwords <span>*</span></label>
                                <input type="password" name="motdepasse">
                            </div>
                            <div class="login_submit">
                                <button class="btn btn-md btn-black-default-hover mb-4" type="submit">Se connecter</button>
                               <a href="controler?redirigerVersInscription='**'">S'inscrire</a>

                            </div>
                        </form>
                       
                    </div>
                </div>
                <!--login area start-->

             
            </div>
        </div>
    </div> <!-- ...:::: End Customer Login Section :::... -->
    
      <%@ include file="footer.jsp" %>