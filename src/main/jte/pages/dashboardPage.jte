@import java.util.List
@import io.gshockv.shrtr.infra.web.dto.ShortLinkResponse

@param List<ShortLinkResponse> links

@template.layouts.pageLayout(
headerRightContent = @`
`,
pageContent = @`

  <div class="overflow-x-auto m-2 rounded-box border border-base-300 bg-base-100">
    <table class="table table-zebra">
      <thead>
        <tr>
          <th>ID</th>
          <th>Short URL</th>
          <th>Original URL</th>
          <th>Hits Count</th>
          <th></th>
        </tr>
      </thead>

      <tbody>
        @for(ShortLinkResponse link : links)
          <tr class="hover:bg-base-300">
            <td>${link.getId()}</td>
            <td>
              <a href="${link.getShortUrl()}" target="_blank" class="link">
                ${link.getShortUrl()}
              </a>
            </td>
            <td>${link.getOriginalUrl()}</td>
            <td>0</td>
            <td>
              <button class="btn btn-outline btn-xs btn-error">Delete</button>
            </td>
          </tr>
        @endfor
      </tbody>
    </table>
  </div>

`)