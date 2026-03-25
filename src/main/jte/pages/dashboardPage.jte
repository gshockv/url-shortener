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
          @template.components.shortLinkTableRow(link = link)
        @endfor
      </tbody>
    </table>
  </div>

`)