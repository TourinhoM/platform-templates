# gitops-${{ values.name }}

Repositório de **deploy** (manifests kustomize) de
[`${{ values.name }}`](https://github.com/tourinho-labs/${{ values.name }}).

> ⚙️ **Gerenciado por máquina — não edite à mão.**
>
> Todo o conteúdo deste repo (base, overlays, claims, ExternalSecrets) é
> **derivado** do `platform.yaml` na raiz do repo de código, pelo renderer da
> plataforma (`render-gitops`). A fonte única de verdade é o `platform.yaml`;
> este repo é só o alvo materializado.
>
> Para mudar o deploy, edite o `platform.yaml` no repo de código — o push
> re-renderiza este repo automaticamente.

No primeiro momento após o scaffold este repo nasce só com este README. O
primeiro push de `platform.yaml` no repo de código dispara o `render-gitops`,
que materializa `base/` + `overlays/dev` + `stages/{hml,prod}`. A imagem chega
em seguida pela promoção de tag do `ci-tag`.
