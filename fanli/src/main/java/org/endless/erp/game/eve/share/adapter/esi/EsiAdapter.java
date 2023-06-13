package org.endless.erp.game.eve.share.adapter.esi;

import static org.endless.erp.share.constant.ConstantResource.REGION_ID;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.endless.erp.share.util.url.UrlFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * EsiAdapter
 *
 * @author Deng Haozhi
 * @date 2023/4/23 16:59
 * @since 0.0.2
 */
@Log4j2
@Component
public class EsiAdapter {

  private final RestTemplate restTemplate;

  public EsiAdapter(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public HttpHeaders getHeader(String service, String scenes) {

    String url = getUrl(service, scenes);

    try {

      return restTemplate.headForHeaders(url);

    } catch (Exception e) {

      e.printStackTrace();
      return null;
    }
  }

  public Integer getPages(String service, String scenes) {

    try {

      return Integer.parseInt(
          Objects.requireNonNull(getHeader(service, scenes).get("X-Pages")).get(0));

    } catch (Exception e) {

      e.printStackTrace();
      return 0;
    }
  }

  public List<?> get(String service, String scenes, Map<String, String> paras) {

    String url = getUrl(service, scenes);

    url = url + UrlFormatter.para(paras);

    try {

      return restTemplate.getForObject(url, List.class, paras);

    } catch (HttpClientErrorException.NotFound e) {
      return null;
    } catch (Exception e) {
      log.error(e.getMessage());
      return null;
    }
  }

  protected String getUrl(String service, String scenes) {
    return "https://esi.evepc.163.com/latest/" + service + "/" + REGION_ID + "/" + scenes;
  }
}
