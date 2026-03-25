@param String shortLink

<div class="flex flex-1 border border-dashed rounded-md p-4">
  <div class="flex-1">
    <a class="link w-full flex-1 text-lg" href="${shortLink}" target="_blank">
      ${shortLink}
    </a>
  </div>
  <button
    class="btn btn-soft btn-primary btn-sm place-self-end"
    onclick="navigator.clipboard.writeText('${shortLink}')">
    Copy
  </button>
</div>
