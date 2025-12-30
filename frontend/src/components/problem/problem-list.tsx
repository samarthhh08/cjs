import type { Problem } from "@/types/problem";
import { Card } from "../ui/card";
import { Link } from "react-router-dom";
import { Pencil, Trash2 } from "lucide-react";
import { Button } from "../ui/button";

type Props = {
  problems: Pick<Problem, "title" | "difficulty" | "id" | "slug">[];
  admin?: boolean;
};

export default function ProblemList({ problems, admin = false }: Props) {
  return (
    <Card className="flex flex-col px-2 py-3 gap-y-2 w-full sm:max-w-[800px]">
      {problems.map((problem, i) => (
        <div key={problem.id} className="flex flex-col gap-y-2">
          <div className="flex items-center justify-between px-2 py-2">
            {/* Title */}
            <Link
              to={`/problems/${problem.slug}/solution`}
              className="font-medium hover:underline"
            >
              {problem.title}
            </Link>

            {/* Difficulty */}
            {!admin && (
              <span
                className={`uppercase text-xs font-bold ${
                  problem.difficulty === "HARD"
                    ? "text-orange-600"
                    : problem.difficulty === "EASY"
                    ? "text-green-600"
                    : "text-yellow-600"
                }`}
              >
                {problem.difficulty}
              </span>
            )}

            {/* Admin Actions */}
            {admin && (
              <div className="flex items-center gap-x-2">
                {/* Edit */}
                <Button size="icon" variant="ghost" asChild>
                  <Link to={`/admin/problems/${problem.slug}/edit`}>
                    <Pencil className="w-4 h-4" />
                  </Link>
                </Button>

                {/* Delete */}
                <Button
                  size="icon"
                  variant="ghost"
                  className="text-red-500 hover:text-red-600"
                  onClick={() => {
                    // 🔥 hook delete handler here
                    console.log("Delete problem:", problem.id);
                  }}
                >
                  <Trash2 className="w-4 h-4" />
                </Button>
              </div>
            )}
          </div>

          {i !== problems.length - 1 && (
            <div className="border-b border-gray-200" />
          )}
        </div>
      ))}
    </Card>
  );
}
