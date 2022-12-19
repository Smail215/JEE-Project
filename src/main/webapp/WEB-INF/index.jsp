<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="header.jsp" %>
<br>
<br>
  <!-- ...:::: Start Shop Section:::... -->
    <div class="shop-section">
        <div class="container">
            <div class="row flex-column-reverse flex-lg-row">
                <div class="col-lg-3">

				<%@ include file="sidebar.jsp" %>

                    </div> <!-- End Sidebar Area -->
                </div>
                
                
                
                
                
                <div class="col-lg-9">
                   

                    <!-- Start Tab Wrapper -->
                    
                      <div class="sort-product-tab-wrapper">
                        <div class="container">
                            <div class="row">
                                <div class="col-12">
                                    <div class="tab-content tab-animate-zoom">
                                        <!-- Start Grid View Product -->
                                        <div class="tab-pane active show sort-layout-single" id="layout-3-grid">
                                            <div class="row">
                                            <c:if test="${message!=null}">
                                               <div class="alert alert-info" role="alert">${message}</div>
                                               </c:if>
                                                 <c:forEach var="i" begin="0" end="2"> 
                                   				 <c:forEach items="${listeProduits}" var="p">
                                   		
                                                 <div class="col-xl-4 col-sm-6 col-12">
                                                
                                                    <!-- Start Product Default Single Item -->
                                                    <div class="product-default-single-item product-color--golden"
                                                        data-aos="fade-up" data-aos-delay="0">
                                                        <div class="image-box">
                                                            <a href="controler?produit=${p.id}" class="image-link">
                                                                <img src="img/${p.image}"
                                                                    alt="">

                                                            </a>
                                                            <div class="action-link">
                                                                <div class="action-link-left">
                                                                     <a href="controler?panier=${p.id}&page=<%=request.getContextPath()%>" >Add to Cart</a>
                                                                </div>
                                                                <div class="action-link-right">
                                                                   
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="content">
                                                            <div class="content-left">
                                                                <h6 class="title"><a
                                                                        href="controler?produit=${p.id}">${p.nom}</a></h6>
                                                                <ul class="review-star">
                                                                    <li class="fill"><i class="ion-android-star"></i>
                                                                    </li>
                                                                    <li class="fill"><i class="ion-android-star"></i>
                                                                    </li>
                                                                    <li class="fill"><i class="ion-android-star"></i>
                                                                    </li>
                                                                    <li class="fill"><i class="ion-android-star"></i>
                                                                    </li>
                                                                    <li class="empty"><i class="ion-android-star"></i>
                                                                    </li>
                                                                </ul>
                                                            </div>
                                                            <div class="content-right">
                                                                <span class="price">${p.prix}DH</span>
                                                            </div>
                      
                                                        </div>
                      
                                                    </div>
                                                    </div>
                                                 
                                 </c:forEach>
                                   </c:forEach> 
                          
                                                    <!-- End Product Default Single Item -->
                                                </div>
                         
                                                </div>
                              
                                            </div>
                               
                                        </div>
                                
                                         <!-- End List View Product -->
                                         
                                         
                                         
                                         
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> <!-- End Tab Wrapper -->

                    <!-- Start Pagination -->
                    <div class="page-pagination text-center" data-aos="fade-up" data-aos-delay="0">
                        <ul>
                            <li><a class="active" href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#"><i class="ion-ios-skipforward"></i></a></li>
                        </ul>
                    </div> <!-- End Pagination -->
                </div>
            </div>
        </div>
    </div> <!-- ...:::: End Shop Section:::... -->
    <br>
     <div class="product-default-slider-section section-fluid section-inner-bg">
        <!-- Start Section Content Text Area -->
        <div class="section-title-wrapper" data-aos="fade-up" data-aos-delay="0">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="section-content-gap">
                            <div class="secton-content">
                                <h3 class="section-title">PRODUITS CONSULTES</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

            <!-- Start Section Content Text Area -->
        <div class="product-wrapper" data-aos="fade-up" data-aos-delay="0">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="product-slider-default-1row default-slider-nav-arrow">
                            <!-- Slider main container -->
                            <div class="swiper-container product-default-slider-4grid-1row">
                                <!-- Additional required wrapper -->
                                <div class="swiper-wrapper">
                                
                                
                                
                                
                                
                                <!--************************* BOUCLE************************************ -->
                                 <!-- Start Product Default Single Item -->
                                 <c:forEach items="${produitConsulte}" var="pc">
                                    <div class="product-default-single-item product-color--pink swiper-slide">
                                        <div class="image-box">
                                            <a href="controler?produit=${pc.id}" class="image-link">
                                                <img src="img/${pc.image}" alt="">
                                            </a>
                                            <div class="action-link">
                                                <div class="action-link-left">
                                                    <a href="controler?panier=${pc.id}" >Add to Cart</a>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="content">
                                            <div class="content-left">
                                                <h6 class="title"><a href="controler?produit=${pc.id}">${pc.nom}</a></h6>
                                                <ul class="review-star">
                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                    <li class="fill"><i class="ion-android-star"></i></li>
                                                    <li class="empty"><i class="ion-android-star"></i></li>
                                                </ul>
                                            </div>
                                            <div class="content-right">
                                                <span class="price">${pc.prix}</span>
                                            </div>

                                        </div>
                                    </div>
                                    <!-- End Product Default Single Item BOucle fin-->
                                    </c:forEach>
                                </div>
                            </div>
                            <!-- If we need navigation buttons -->
                            <div class="swiper-button-prev"></div>
                            <div class="swiper-button-next"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
                                
                                
    
     <%@ include file="footer.jsp" %>