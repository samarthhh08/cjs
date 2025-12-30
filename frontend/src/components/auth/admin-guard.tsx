import { type ReactNode } from "react";

import { useAuth } from "@/auth/useAuth";
import Unauthorized from "../unauthorized";

type Props = {
  children: ReactNode;
};

export default function AdminGuard({ children }: Props) {
  const { user, isLoading } = useAuth();

  if (isLoading) {
    return null;
  }

  if (!user || user.role !== "ADMIN") {
    return <Unauthorized></Unauthorized>;
  }

  return children;
}
