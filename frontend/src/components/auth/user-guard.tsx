import { type ReactNode } from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "@/auth/useAuth";

type Props = {
  children: ReactNode;
};

export default function UserGuard({ children }: Props) {
  const { user, isLoading } = useAuth();

  if (isLoading) {
    return null;
  }


  if (!user) {
    return <Navigate to="/signin" replace />;
  }

  return children;
}
