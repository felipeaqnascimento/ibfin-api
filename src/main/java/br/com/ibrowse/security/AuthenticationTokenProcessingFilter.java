package br.com.ibrowse.security;
//package br.gov.mg.uberlandia.eautorizaservice.security;
//
//public class AuthenticationTokenProcessingFilter /*
//													 * extends
//													 * AbstractAuthenticationProcessingFilter
//													 */ {
//
//	// private static final Logger LOGGER =
//	// LoggerFactory.getLogger(AuthenticationTokenProcessingFilter.class);
//	// private static final String SECURITY_TOKEN_KEY = "X-Auth-Token";
//	// private static final String FILTER_APPLIED = "TokenFilter";
//	//
//	// @Autowired
//	// UsuariosJpaRepository usuariosJpaRepository;
//	//
//	// protected AuthenticationTokenProcessingFilter(RequestMatcher
//	// requiresAuthenticationRequestMatcher) {
//	// super(requiresAuthenticationRequestMatcher);
//	// }
//	//
//	// public UsuariosJpaRepository getUsuariosJpaRepository() {
//	// return usuariosJpaRepository;
//	// }
//	//
//	// public void setUsuariosJpaRepository(UsuariosJpaRepository
//	// usuariosJpaRepository) {
//	// this.usuariosJpaRepository = usuariosJpaRepository;
//	// }
//	//
//	// @Override
//	// public void doFilter(ServletRequest req, ServletResponse res, FilterChain
//	// chain)
//	// throws IOException, ServletException {
//	// HttpServletRequest request = (HttpServletRequest) req;
//	// HttpServletResponse response = (HttpServletResponse) res;
//	//
//	// if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
//	// chain.doFilter(request, response);
//	// return;
//	// }
//	//
//	// if (request.getAttribute(FILTER_APPLIED) != null) {
//	// chain.doFilter(request, response);
//	// return;
//	// }
//	//
//	// request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
//	//
//	// if (!requiresAuthentication(request, response)) {
//	// chain.doFilter(request, response);
//	// return;
//	// }
//	//
//	// Authentication authResult;
//	// try {
//	// authResult = attemptAuthentication(request, response);
//	// } catch (AuthenticationException failed) {
//	// unsuccessfulAuthentication(request, response, failed);
//	// return;
//	// }
//	//
//	// try {
//	// successfulAuthentication(request, response/*, chain*/ , authResult);
//	// } catch (NestedServletException ex) {
//	// LOGGER.warn(ex.getMessage(), ex);
//	// if (ex.getCause() instanceof AccessDeniedException) {
//	// unsuccessfulAuthentication(request, response, new
//	// LockedException("Forbidden"));
//	// }
//	// }
//	// }
//	//
//	// @Override
//	// public Authentication attemptAuthentication(HttpServletRequest request,
//	// HttpServletResponse response)
//	// throws AuthenticationException, IOException, ServletException {
//	// String token = request.getHeader(SECURITY_TOKEN_KEY);
//	//
//	// AbstractAuthenticationToken userAuthenticationToken =
//	// authUserByToken(token);
//	// if (userAuthenticationToken == null) {
//	// throw new AuthenticationServiceException(MessageFormat.format("Error |
//	// {0}", "Bad Token"));
//	// }
//	//
//	// return userAuthenticationToken;
//	// }
//	//
//	// private AbstractAuthenticationToken authUserByToken(String token) {
//	// AbstractAuthenticationToken authToken = null;
//	// try {
//	// if (token != null) {
//	// String username = UsuarioTokenUtils.extractUsername(token);
//	// UsuariosEntity usuario =
//	// this.usuariosJpaRepository.findByNrCpf(Long.parseLong(username));
//	//
//	// if (UsuarioTokenUtils.validateToken(usuario, token)) {
//	// LOGGER.info("valid token found");
//	// authToken = new UsernamePasswordAuthenticationToken(usuario.getNrCpf(),
//	// usuario.getDsSenha(),
//	// Arrays.asList(new RoleUser()));
//	// } else {
//	// LOGGER.info("invalid token");
//	// }
//	// } else {
//	// LOGGER.info("no token found");
//	// }
//	// } catch (Exception e) {
//	// LOGGER.error("Error during authUserByToken", e);
//	// }
//	//
//	// return authToken;
//	// }
//	//
//	// protected void successfulAuthentication(HttpServletRequest request,
//	// HttpServletResponse response,
//	// Authentication authResult) throws IOException, ServletException {
//	// SecurityContextHolder.getContext().setAuthentication(authResult);
//	//
//	// getSuccessHandler().onAuthenticationSuccess(request, response,
//	// authResult);
//	// }
//}
