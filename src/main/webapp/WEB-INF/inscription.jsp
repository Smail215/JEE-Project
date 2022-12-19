<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>

<br>
<br>

 <!-- ...:::: Start Customer Login Section :::... -->
    <div class="customer-login">
        <div class="container">
            <div class="row">
                <!--register area start-->
                <div class="col-lg-6 col-md-6">
                    <div class="account_form register" data-aos="fade-up" data-aos-delay="200">
                        <h3>Register</h3>
                       <c:if test="${message!=null}">
                         
                        <div class="alert alert-danger" role="alert">${message}</div>
                        </c:if>
                        <form action="controler" method="POST">
                        <input type="text" name="inscription" hidden>
                         <div class="default-form-box">
                                <label>Login <span>*</span></label>
                                <input type="text" name="login">
                            </div>
                        
                        <div class="default-form-box">
                                <label>Nom et Prénom <span>*</span></label>
                                <input type="text" name="nom">
                            </div>
                        
                        
                            <div class="default-form-box">
                                <label>Email <span>*</span></label>
                                <input type="email" name="email">
                            </div>
                            <div class="default-form-box">
                                <label>Mot de passe <span>*</span></label>
                                <input type="password" name="motdepasse">
                            </div>
                            <div class="login_submit">
                                <button class="btn btn-md btn-black-default-hover" type="submit">S'inscrire</button>
                            </div>
                        </form>
                    </div>
                </div>
                <!--register area end-->
            </div>
        </div>
    </div> <!-- ...:::: End Customer Login Section :::... -->
    
      <%@ include file="footer.jsp" %>