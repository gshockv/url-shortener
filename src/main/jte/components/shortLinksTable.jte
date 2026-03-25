@import io.gshockv.shrtr.domain.ShortLinkPage
@import io.gshockv.shrtr.domain.ShortLink

@param ShortLinkPage<ShortLink> page

<div class="overflow-auto m-2 rounded-box border border-base-300 bg-base-100">
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
    @for(ShortLink link : page.getItems())
      @template.components.shortLinkTableRow(link = link)
    @endfor
    </tbody>
  </table>
</div>

@if(page.getPagesCount() > 1)
  <div class="flex w-full justify-center mt-2">
    <div class="join">

      @for(int indx = 0; indx < page.getPagesCount(); indx++)
        <button class="join-item btn ${indx == page.getPage() ? "btn-active" : ""}"
          hx-get="/links/table?page=${indx}"
          hx-target="#tableContainer">
          ${indx + 1}
        </button>
      @endfor
    </div>
  </div>
@endif
