package com.bitflowsoft.scrapeej.core.browser;

public class BrowserProxy {

  private String proxyServer;
  private String username;
  private String password;

  private BrowserProxy() {}

  public static ProxyBuilder builder() {
    return new ProxyBuilder();
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public String getProxyServer() {
    return proxyServer;
  }

  public static class ProxyBuilder {

    private final BrowserProxy browserProxy;

    ProxyBuilder() {
      this.browserProxy = new BrowserProxy();
    }

    public ProxyBuilder proxyServer(final String server) {
      browserProxy.proxyServer = server;
      return this;
    }

    public ProxyBuilder username(final String username) {
      browserProxy.username = username;
      return this;
    }

    public ProxyBuilder password(final String password) {
      browserProxy.password = password;
      return this;
    }

    public BrowserProxy build() {
      return browserProxy;
    }
  }
}
