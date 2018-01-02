package ;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Esaa on 27/9/2017.
 */

public class RuntimePermissionsManager 
{
	
    public static boolean deviceNeedCheckPermissionAtRuntime() 
    {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean needCheckPermissionAtRuntime(String permission, final Activity activity)
    {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    public static boolean needCheckPermissionAtRuntime(final Activity activity, String permission)
    {
        return needCheckPermissionAtRuntime(permission, activity);
    }

    /**
     * @return true if one permissions need Check at Runtime
     */
    public static boolean needCheckPermissionAtRuntime(final String permissions[], final Activity activity)
    {
        for (String p : permissions) {
            if (needCheckPermissionAtRuntime(p, activity))
                return true;
        }
        return false;
    }

    /**
     * @return true if one permissions need Check at Runtime
     */
    public static boolean needCheckPermissionAtRuntime(final Activity activity, final String permissions[])
    {
        return needCheckPermissionAtRuntime(permissions, activity);
    }

    /**
     * @return the permissions that need check at runtime.
     */
    public static String[] neededPermissionsAtRuntime(final String permissions[], final Activity activity)
    {
        ArrayList<String> permissionsNeedCheckList = new ArrayList<String>();
        for (String p: permissions) {
            if (needCheckPermissionAtRuntime(activity, p))
                permissionsNeedCheckList.add(p);
        }
        return listToArray(permissionsNeedCheckList);
    }

    /**
     * @return the permissions that need check at runtime
     */
    public static String[] neededPermissionsAtRuntime(final Activity activity, final String permissions[])
    {
        return neededPermissionsAtRuntime(permissions, activity);
    }

    /**
     * @return the permissions that need check at runtime
     */
    public static String[] neededPermissionsAtRuntime(final Activity activity, final List<String> permissions) {
        return neededPermissionsAtRuntime(listToArray(permissions), activity);
    }

    /**
     * @return the permissions that need check at runtime
     */
    public static String[] neededPermissionsAtRuntime(final List<String> permissions, final Activity activity)
    {
        return neededPermissionsAtRuntime(activity, permissions);
    }

    /**
     * @return true if the context has the permission.
     */
    public static boolean checkPermission(final String permission, final Context context)
    {
        return (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * @return true if the context has the permission.
     */
    public static boolean checkPermission(final Context context, final String permission)
    {
        return checkPermission(permission, context);
    }

    /**
     *  @return the permissions that are activate
     * */
    public static String[] getPermissionsEnabled(final String[] permissions, final Context context)
    {
        return getPermissionByCheck(permissions,context,true);
    }

    /**
     *  @return the permissions that are activate
     * */
    public static String[] getPermissionsEnabled(final Context context,final String[] permissions)
    {
        return getPermissionsEnabled(permissions,context);
    }

    /**
     *  @return the permissions that are activate
     * */
    public static String[] getPermissionsEnabled(final List<String> permissions, final Context context)
    {
        return getPermissionsEnabled(listToArray(permissions),context);
    }

    /**
     *  @return the permissions that are activate
     * */
    public static String[] getPermissionsEnabled(final Context context,final List<String> permissions)
    {
        return getPermissionsEnabled(permissions,context);
    }

    /**
     *  @return the permissions that are missing
     * */
    public static String[] getMissingPermissions(final String[] permissions, final Context context)
    {
        return getPermissionByCheck(permissions,context,false);
    }

    /**
     *  @return the permissions that are missing
     * */
    public static String[] getMissingPermissions(final Context context,final String[] permissions)
    {
        return getMissingPermissions(permissions,context);
    }

    /**
     *  @return the permissions that are missing
     * */
    public static String[] getMissingPermissions(final List<String> permissions, final Context context)
    {
        return getMissingPermissions(listToArray(permissions),context);
    }

    /**
     *  @return the permissions that are missing
     * */
    public static String[] getMissingPermissions(final Context context,final List<String> permissions) {
        return getMissingPermissions(permissions,context);
    }

    private static String[] getPermissionByCheck(final String[] permissions, final Context context, boolean check) 
    {
        ArrayList<String> permissionsList = new ArrayList<String>();
        for (String p : permissions) {
            if (checkPermission(context, p) == check) {
                permissionsList.add(p);
            }
        }

        return listToArray(permissionsList);
    }

    private static String[] listToArray(List<String> stringList)
    {
        String[] strings = new String[stringList.size()];
        for (int i = 0; i < stringList.size(); i++) {
            strings[i] = stringList.get(i);
        }

        return strings;
    }

}
