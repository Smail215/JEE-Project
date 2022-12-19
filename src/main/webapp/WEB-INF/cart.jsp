<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>

<br>
<br>
<br>
<br>
<script type="text/javascript">



</script>
<!-- IFFF CONDITION*********** -->
 <%
 int nbr=request.getCookies().length-2;
 if(nbr==0){
 
 %>
<!-- ...::::Start About Us Center Section:::... -->
    <div class="empty-cart-section section-fluid">
        <div class="emptycart-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-md-10 offset-md-1 col-xl-6 offset-xl-3">
                        <div class="emptycart-content text-center">
                            <div class="image">
                                <img class="img-fluid" src="assets/images/emprt-cart/empty-cart.png" alt="">
                            </div>
                            <h4 class="title">Your Cart is Empty</h4>
                            <h6 class="sub-title">Sorry Mate... No item Found inside your cart!</h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- ...::::End  About Us Center Section:::... -->
<%}else{ %>

<!-- ...:::: Start Cart Section:::... -->
    <div class="cart-section">
        <!-- Start Cart Table -->
        <div class="cart-table-wrapper" data-aos="fade-up" data-aos-delay="0">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                      <c:if test="${message!=null}">
                         <c:forEach items="${message}" var="m">
                        <div class="alert alert-danger" role="alert">${m}</div>
                        </c:forEach>
                        </c:if>
                        <div class="table_desc">
                            <div class="table_page table-responsive">
                                <table>
                                    <!-- Start Cart Table Head -->
                                    <thead>
                                        <tr>
                                            <th class="product_remove">Delete</th>
                                            <th class="product_thumb">Image</th>
                                            <th class="product_name">Product</th>
                                            <th class="product-price">Price</th>
                                            <th class="product_quantity">Quantity</th>
                                            <th class="product_total">Total</th>
                                        </tr>
                                    </thead> <!-- End Cart Table Head -->
                                    <tbody>
                                    
                                   <%
                                   float somme=0;
                                   %>   
                                   <form action='controler' method='POST'>
                                   <% int i=0; %>
                                        <!-- Start Cart Single Item-->
                                         <c:forEach items="${p}" var="p" >
                                        <tr>
                                            <td class="product_remove">  <a href="controler?supprimer=${p.id}"><i class="fa fa-trash-o"></i></a>
                                            </td>
                                            <td class="product_thumb"><a href="controler?produit=${p.id}"><img
                                                        src="img/${p.image}"
                                                        alt=""></a></td>
                                         <td class="product_name"><a href="controler?produit=${p.id}">${p.nom}</a></td>
                                            <td class="product-price">${p.prix}</td>
                                            <td class="product_quantity"><label>Quantity</label> <input min="1"
                                                   value="${p.quantite}" type="number" name="quantite<%=i %>"></td>
                                            <td class="product_total">${p.prix*p.quantite}</td>
                                            <% i+=1; %>
                                            
                                           
                                            
                                        </tr> <!-- End Cart Single Item-->
                                        </c:forEach>
                                        
                                        <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        
                                        <td class="product_total">${somme}</td>
                                        
                                        </tr>
                                      
                                       
                                    </tbody>
                                </table>
                            </div>
                            <div class="cart_submit">
                             <button class="btn btn-md btn-golden" name="procedureAchat" type="submit">Proceed to Checkout</button>
                                <button class="btn btn-md btn-golden" name="modifierCart" type="submit">update cart</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End Cart Table -->

        
    </div> <!-- ...:::: End Cart Section:::... -->
    <%} %>
    
      <%@ include file="footer.jsp" %>