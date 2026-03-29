@import io.gshockv.shrtr.domain.ShortLink

@param ShortLink link

<tr class="hover:bg-base-300">
  <td>${link.getId()}</td>
  <td>
    <a href="${link.getShortUrl()}" target="_blank" class="link">
      ${link.getShortUrl()}
    </a>
  </td>
  <td>${link.getOriginalUrl()}</td>
  <td>
    <button
      class="btn btn-outline btn-xs btn-error"
      hx-delete="/delete-short-link/${link.getId()}"
      hx-target="closest tr"
      hx-swap="outerHTML swap:0.2s"
    >
      Delete
    </button>
  </td>
</tr>