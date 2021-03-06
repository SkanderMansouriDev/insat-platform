import { Routes } from '@angular/router';

import { activateRoute, passwordRoute, passwordResetFinishRoute, passwordResetInitRoute, registerRoute, settingsRoute } from './';
import { inviteRoute } from 'app/account/invite/invite.route';
import { historyRoute } from 'app/history/history.route';
import { userhistoryRoute } from 'app/user-history/user-history.route';

const ACCOUNT_ROUTES = [
    activateRoute,
    passwordRoute,
    passwordResetFinishRoute,
    passwordResetInitRoute,
    registerRoute,
    settingsRoute,
    historyRoute,
    userhistoryRoute,
    inviteRoute
];

export const accountState: Routes = [
    {
        path: '',
        children: ACCOUNT_ROUTES
    }
];
