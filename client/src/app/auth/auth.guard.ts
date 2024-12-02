import { CanActivateFn, Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  const loginService = inject(LoginService);
  const router = inject(Router);
  const usuarioLogado = loginService.usuarioLogado;
  let url = state.url;
  if (usuarioLogado) {
    if (
      route.data?.['role'] &&
      route.data?.['role'].indexOf(usuarioLogado.tipoPermissao) === -1
    ) {
      router.navigate(['/login'], {
        queryParams: { error: 'Proibido o acesso a ' + url },
      });
      return false;
    }
    return true;
  }
  router.navigate(['/login'], {
    queryParams: {
      error: 'Deve fazer o login antes de acessar ' + url,
    },
  });
  return false;
};
