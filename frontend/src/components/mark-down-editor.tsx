import { useState } from "react";
import ReactMarkdown from "react-markdown";
import remarkGfm from "remark-gfm";

import { Textarea } from "@/components/ui/textarea";
import { Button } from "@/components/ui/button";
import { Card } from "@/components/ui/card";
import { cn } from "@/lib/utils";

type Props = {
  value: string;
  onChange: (value: string) => void;
  error?: string;
};

export function MarkdownEditor({ value, onChange, error }: Props) {
  const [mode, setMode] = useState<"edit" | "preview">("edit");

  return (
    <Card className="border">
      {/* Header (fixed) */}
      <div className="flex items-center justify-between px-3 py-2 border-b">
        <span className="font-medium">Description</span>

        <div className="flex gap-2">
          <Button
            type="button"
            size="sm"
            variant={mode === "edit" ? "default" : "outline"}
            onClick={() => setMode("edit")}
          >
            Edit
          </Button>
          <Button
            type="button"
            size="sm"
            variant={mode === "preview" ? "default" : "outline"}
            onClick={() => setMode("preview")}
          >
            Preview
          </Button>
        </div>
      </div>

      {/* Body (fixed height + scroll) */}
      <div className="h-[400px] overflow-y-auto p-3">
        {mode === "edit" ? (
          <Textarea
            value={value}
            onChange={(e) => onChange(e.target.value)}
            className="h-full resize-none font-mono"
            placeholder={`## Problem Statement

Write a function that...

### Example
Input: ...
Output: ...
`}
          />
        ) : (
          <div className={cn("prose prose-sm max-w-none", "dark:prose-invert")}>
            {value ? (
              <ReactMarkdown remarkPlugins={[remarkGfm]}>{value}</ReactMarkdown>
            ) : (
              <p className="text-muted-foreground">Nothing to preview</p>
            )}
          </div>
        )}

        {error && <p className="text-sm text-red-500 mt-2">{error}</p>}
      </div>
    </Card>
  );
}
