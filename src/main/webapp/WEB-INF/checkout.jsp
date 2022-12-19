<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>

<br>
<br>

 <!-- ...:::: Start Checkout Section:::... -->
    <div class="checkout-section">
        <div class="container">
             <c:if test="${message!=null}">
                         
                        <div class="alert alert-danger" role="alert">${message}</div>
                        </c:if>
            <!-- Start User Details Checkout Form -->
            <div class="checkout_form" data-aos="fade-up" data-aos-delay="400">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <form action="controler" method="POST">
                            <h3>Formulaire</h3>
                            <div class="row">                         
                                <div class="col-lg-6">
                                    <div class="default-form-box">
                                        <label>Pays <span>*</span></label>
                                        <input type="text" name='pays'>
                                    </div>
                                </div> 
                                <div class="col-12">
                                    <div class="default-form-box">
                                        <label>Adresse <span>*</span></label>
                                        <input type="text" name='adresse'>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="default-form-box">
                                        <label>Telephone<span>*</span></label>
                                        <input type="text" name="telephone">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="default-form-box">
                                        <label>Numero de la carte<span>*</span></label>
                                        <input type="text" name="carte">
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="default-form-box">
                                        <label>Code<span>*</span></label>
                                        <input type="password" name="code" >
                                    </div>
                                </div>
                               
                            </div>
                        
                    </div>
                    <div class="col-lg-6 col-md-6">
                       
                            <h3>Vos produits</h3>
                            <div class="order_table table-responsive">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Product</th>
                                            <th>Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${p}" var="p" >
                                        <tr>
                                            <td>${p.nom}<strong> (${p.quantite})</strong></td>
                                            <td> ${p.prix*p.quantite}</td>
                                        </tr>
									</c:forEach>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>Prix des produits</th>
                                            <td><strong>${somme}</strong></td>
                                        </tr>
                                        <tr>
                                            <th>Expedition</th>
                                            <td>${expedition}0</td>
                                        </tr>
                                        <tr class="order_total">
                                            <th>Prix total</th>
                                            <td><strong>${expedition+somme}</strong></td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>

                          <button class="btn btn-md btn-black-default-hover" name="validerPayement" value="" type="submit">Valider</button>

                        </form>
                    </div>
                </div>
            </div> <!-- Start User Details Checkout Form -->
        </div>
    </div><!-- ...:::: End Checkout Section:::... -->

    
      <%@ include file="footer.jsp" %>